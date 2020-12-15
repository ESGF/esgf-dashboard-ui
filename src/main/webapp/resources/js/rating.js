function ratingController ($scope, $http) {
		
	$scope.rateExperience = function () {
		
		//var rate1 = $scope.getElementById("rate1").value;
		var rate1 = "10";
		var rate2 = document.getElementById("rate2").value;
		var rate3 = document.getElementById("rate3").value;
		var rate4 = document.getElementById("rate4").value;
		
		$http.get("/esgf-dashboard-ui/rateExperience", {params: {rate1: rate1, rate2: rate2, rate3 : rate3, rate4 : rate4}}).success(function (data) {
			
/*		     	if (data.length) {
		        }
		        else {
		 		} */
		
		});
    }	
}