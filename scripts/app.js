/**
 * Created by Phani dutt on 25/05/2016.
 */
var app_Filters;
app_Filters = angular.module('app_Filters', []);

app_Filters.controller('signCtrl',function ($scope) {
    $scope.submitForm = function() {
        if ($scope.form.$valid) {
            alert('all are valid');
        }
else {
          alert('not valid');
        }
    };



});
