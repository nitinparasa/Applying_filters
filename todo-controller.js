angular
  .module('frontend.todo')
  .controller('TodoCtrl', function ($scope, $window, $resource, $http) {
    var HelloWorld=$resource('http://localhost.8080/api/hello-world');
    'use strict';
    $scope.greeting=HelloWorld.get();

    $scope.todos = JSON.parse($window.localStorage.getItem('todos') || '[]');
    $scope.$watch('todos', function (newTodos, oldTodos) {
      if (newTodos !== oldTodos) {
        $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
      }
    }, true);

    $scope.add = function () {
      var todo = {label: $scope.label, isDone: false};
      $scope.todos.push(todo);
      $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
      $scope.label = '';
    };

    $scope.check = function () {
      this.todo.isDone = !this.todo.isDone;
    };

    $http({
      method: 'GET',
      url: 'http://localhost:8080/api/filter'
    }).then(function successCallback(filters) {
      alert(filters);
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });

  });
