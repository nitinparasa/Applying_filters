/**
 * Created by Phani dutt on 25/05/2016.
 */
var appFilters = angular.module('appFilters', [
  'ngRoute'
])
.controller('signCtrl',function ($scope, $http) {

  $scope.submitForm = function (response) {
    if ($scope.form.$valid) {
      $http({
        method: 'POST',
        url: 'http://localhost:8080/api/users',
        data: $scope.user
      }).then(function (response) {
      })
    }
  }
})
  .config(function ($routeProvider) {
    'use strict';
    $routeProvider

      .when('/default',{
        controller: 'SigninCtrl',
        templateUrl: 'default.html'
      })

      .when('/',{
        controller:'SigninCtrl',
        templateUrl :'index.html'

      })
      .otherwise({
        redirectTo: '/default'
      });
  })
appFilters.controller('SigninCtrl',function($scope, $window, $http) {
  'use strict';
  $scope.checkLogin = function () {
    $http({
      method: 'GET',
      url: 'http://localhost:8080/api/users/1'
    }).then(function (response) {
      $scope.uname = response.data;
      alert($scope.uname.username);
      if ($scope.luname === $scope.uname.username && $scope.linpass === $scope.uname.password)
        alert("login successful");
      else alert('please try again');
    })
  };
});
