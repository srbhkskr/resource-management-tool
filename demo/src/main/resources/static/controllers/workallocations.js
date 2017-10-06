var myApp = angular.module('myApp');

myApp.controller('WorkAllocationsController',['$scope', '$http', '$location', '$routeParams','$q','Allocation', function($scope, $http, $location, $routeParams, $q, Allocation){
	console.log('WorkAllocationsController loaded')
	
	$scope.allocation = Allocation;
	
	//$scope.selectedskills = [false,false,false,false,false]
	$scope.selectedemployees = []
	
	$scope.getWorkAllocations = function(){
		$http.get('http://localhost:8080/user/workallocations').then(successCallback, errorCallback);

		function successCallback(response){
			$scope.employees = response.data;
			console.log('All WorkAllocations' + $scope.workallocations);
		}
		function errorCallback(error){
			console.log('WorkAllocationsController error'+error);
		}
	}
	

	
	$scope.getWorkAllocation = function(){
		var id = $routeParams.id
		console.log('workallocations Id :'+id)
		$http.get('http://localhost:8080/user/workallocation/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			$scope.workallocation = response.data;
			
			console.log($scope.workallocation)
		}
		
		function errorCallback(error){
			console.log('WorkAllocationsController error'+error);
		}
		
	}
	
	//$scope.selectedProject = null
	
	$scope.registerWorkAllocation = function(){
		var empid = $routeParams.id
		var projectid = $scope.allocation.selectedProject.id
		console.log('empid: '+empid)
		console.log('workallocation: '+$scope.workallocation)
		console.log('SELECTED PROJ: '+$scope.allocation.selectedProject)
		
		
		$http.post('http://localhost:8080/user/project/'+projectid+'/employee/'+empid+'/workallocation',$scope.workallocation).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/employee/details/'+empid;
		}
		function errorCallback(error){
			console.log('WorkAllocationsController error'+error);
		}
	}
	
	$scope.updateWorkAllocation = function(){
		//var id = $routeParams.id
		$http.put('http://localhost:8080/user/workallocation/',$scope.workallocation).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/employee/details/'+$scope.workallocation.employee.id;
		}
		function errorCallback(error){
			console.log('WorkAllocationsController error'+error);
		}
	}
	
	$scope.deleteWorkAllocation= function(id){
		var empid = $routeParams.id
		$http.delete('http://localhost:8080/user/workallocation/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.reload(true);
		}
		function errorCallback(error){
			console.log('WorkAllocationsController error'+error);
		}
	}
}
]
);