/**
 * Created by Phani dutt on 25/05/2016.
 */
'use strict';
var appFilters = angular.module('appFilters', [
  'ngRoute',
  'ngCookies'
])
.controller('signCtrl',function ($scope, $http) {
$scope.user = {username:"", emailid:"",password:"",confirm_password:""};
  $scope.submitForm = function (response) {
    var data1 = JSON.stringify($scope.user);
    $http({
        method: 'POST',
        url: 'http://localhost:8080/api/users/',
        data: data1
      }).then(function (response) {
      })

    $scope.registerForm.$setPristine();
    $scope.user = {username:"", emailid:"",password:"",confirm_password:""};
  }
    //$scope.registerModal = registerModal;
    //registerModal.modal('hide');

})
  .config(function ($routeProvider) {
    'use strict';
    $routeProvider

      .when('/loginredirect',{
        controller: 'SigninCtrl',
        templateUrl: 'userHome.html'
      })

      .when('/',{
        controller:'SigninCtrl',
        templateUrl :'home.html'

      })

      .when('/terms',{
        controller:'SigninCtrl',
        templateUrl :'terms.html'
      })

      .otherwise({
        redirectTo: '/'
      });
  })
.controller('SigninCtrl',['$scope', '$http', '$window',function($scope, $http, $window) {
  'use strict';
  $scope.checkLogin = function () {
    var updated_url = 'http://localhost:8080/api/users/name/' + $scope.luname;
    $http({
      method: 'POST',
      url: updated_url
    }).then(function (response) {
      $scope.udetails = response.data;
      //alert($scope.udetails);
      var dat = JSON.stringify($scope.udetails);
      alert(dat);
      if ($scope.luname === $scope.udetails.username && $scope.linpass === $scope.udetails.password)
        alert("login successful");
      else {
        alert('please try again');
        $window.location('/');
      }
    })

  };
}])
  .controller('AudioController', ['$scope', '$http', function($scope, $http) {
    'use strict';
    var file_name = $scope.upFile;
    $http({
      method:'POST',
      url: 'http://localhost:8080/api/fileupload',
      data: file_name
    }).then(function(response){
      
    })

  }])


