
var calc = angular.module('calc', []);

var operators = ['+', '-', 'x', '÷'];
calc.controller('calc', function ($scope, $http){
    $scope.operators = operators;

    $scope.submit = function() {
        $http({
            method : "POST",
            url : '/calcvalue',
            // contentType : 'application/json',
            params : {
                "num1" : $scope.num1,
                "num2" : $scope.num2,
                "operator" : $scope.operator
            }
        }).success(function(data) {
            //checking the response data for statusCode
            if (data.statusCode == 401) {
                if ($scope.operator) {
                    $scope.result = "Missing parameters";
                } else if ($scope.num1 && $scope.num2) {
                    $scope.result = "Missing operator";
                } else {
                    $scope.result = "Missing parameter(s) and operator";
                }
            }
            else
            {
                $scope.result = data.result;
            };
        }).error(function(error) {
            $scope.result="Unexpected error occurred";
            $scope.invalid_login = true;
        });
    };
}) ;
