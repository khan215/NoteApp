(function(){

    var app = angular.module('notesApp',['ngRoute', 'ngMaterial']);

   app.config(['$locationProvider', '$routeProvider',
      function ($locationProvider, $routeProvider) {

        $routeProvider
          .when('/notes', {
            templateUrl: '/partials/notes-view.html',
            controller: 'notesController'
          })
          .when('/login', {
             templateUrl: '/partials/login.html',
             controller: 'loginController',
          })
          .otherwise('/');
      }
  ]);

  app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
      $rootScope.$on('$routeChangeStart', function (event) {

          if ($location.path() == "/login"){
             return;
          }

          if (!AuthService.isLoggedIn()) {
              console.log('DENY');
              event.preventDefault();
              $location.path('/login');
          }
      });
  }]);


  app.service('AuthService', function($http){
        var loggedUser = null;

        function login (username, password){
            return $http.post("api/login", {username: username, password: password}).then(function(user){
                loggedUser = user;
            }, function(error){
                loggedUser = null;
            })
        }

        function isLoggedIn(){
            return loggedUser != null;
        }
        return {
            login : login,
            isLoggedIn: isLoggedIn
        }
  });

app.controller('loginController', function($scope, AuthService, $location){

    $scope.invalidCreds = false;
    $scope.login = {
        username : null,
        password : null
    };

    $scope.login = function(){
        AuthService.login($scope.login.username, $scope.login.password).then(function(user){
            console.log(user);
            $location.path("/notes");
        }, function(error){
            console.log(error);
            $scope.invalidCreds = true;
        });
    };
});



app.controller('notesController', function($scope,$http){
    $scope.isEditCreateView = false;
    $scope.isViewNoteDetails = false;

    $scope.notes = [];
    $scope.viewNoteDetails = [];

    $scope.loadDataList = function () {
        $http({
            method : 'GET',
            url : 'notes'
        }).then(function successCallback(response) {
            $scope.notes = response.data;
            }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.form = {
        id : -1,
        summary : "",
        title : "",
        dateTime : new Date()
    };

    $scope.newNoteView = function(){
        $scope.isEditCreateView = true;
        $scope.isViewNoteDetails = false

    };

    $scope.submitNote = function() {

        var method = "";
        var url = "";
        if ($scope.form.id == -1) {
            method = "POST";
            url = 'createNote';
        } else {
            method = "PUT";
            url = 'updateNote/' + $scope.form.id;
        }
        console.log(angular.toJson($scope.form));

        debugger;
        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.form),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then( _success, _error);
    }

    $scope.deleteNote = function (i) {
      var r = confirm("Are you sure you want to delete this note?");
      if (r == true){
          $http({
              method : 'DELETE',
              url : 'deleteNote/' + i
          }).then(_success, _error);
      }
    };

    $scope.viewNote = function(note){
        $scope.isViewNoteDetails = true;
        $scope.isEditCreateView = false;
        console.log(note);
        $scope.viewNoteDetails = note;
    }

    $scope.editNote = function(note) {
        $scope.isEditCreateView = true;
        $scope.isViewNoteDetails = false;

        $scope.form.title = note.title;
        $scope.form.summary = note.summary;
        $scope.form.dateTime = new Date(note.dateTime);
        $scope.form.id = note.id;
    };

    $scope.cancel = function (){
        _clearForm();
        $scope.isEditCreateView = false;
        $scope.isViewNoteDetails = false;
    }


    function _success(response){
        $scope.loadDataList();
        _clearForm();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    function _clearForm() {
        $scope.form.title = "",
        $scope.form.summary = "";
        $scope.form.dateTime = new Date();
        $scope.form.id = -1;
    };


    $scope.loadDataList();
});
}());


