var myApp = angular.module('myApp');

myApp.controller('EmployeesController',['$scope', '$http', '$location', '$routeParams','$q','Allocation', function($scope, $http, $location, $routeParams, $q, Allocation){
	console.log('EmployeesController loaded')
	
	$scope.allocation = Allocation;
	
	//$scope.selectedskills = [false,false,false,false,false]
	$scope.selectedskills = []

	$scope.employee = {}
	$scope.roles = ["Software Engineer","DevOps Engineer","Sr Technical Manager","Technical Manager","Sr Software Engineer","Sr DevOps Engineer","VP","Scrum Master","Trainee"]
	
	$scope.getEmployees = function(){
		$http.get('http://localhost:8080/user/employees').then(successCallback, errorCallback);

		function successCallback(response){
			$scope.employees = response.data;
			console.log('All Employees' + $scope.employees);
		}
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
	}
	

	
	$scope.getEmployee = function(){
		var id = $routeParams.id
		console.log('Employee Id :'+id)
		$http.get('http://localhost:8080/user/employee/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			$scope.employee = response.data;
			
			$scope.employee.skills.forEach(function(skill, index) {
				$scope.selectedskills[skill.id] = true;
				});
			
			
			
			console.log($scope.employee)
		}
		
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
		
	}
	
	$scope.getEmployeeProjects = function(){
		var id = $routeParams.id
		console.log('Employee Id :'+id)
		$http.get('http://localhost:8080/user/employee/'+id+'/projects').then(successCallback, errorCallback);

		function successCallback(response){
			$scope.employee.projects = response.data;
		}
		
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
		
	}
	
	
	$scope.registerEmployee = function(){
		
		
		
		$http.post('http://localhost:8080/user/employee/',$scope.employee).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/employees';
		}
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
	}
	
	$scope.updateEmployee = function(){
		//var id = $routeParams.id
		
		
		$http.put('http://localhost:8080/user/employee/',$scope.employee).then(successCallback2, errorCallback2);

		function successCallback2(response){
			window.location.href='#!/employee/details/'+$scope.employee.id;
		}
		function errorCallback2(error){
			console.log('EmployeesController error'+error);
		}
	}
	
	$scope.deleteEmployee= function(id){
		$http.delete('http://localhost:8080/user/employee/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/employees';
		}
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
	}
	
	
	$scope.addedskillids = []
	$scope.removedskillids = []
	
		$scope.stateChanged1 = function (sid) {
			
			if($scope.selectedskills[sid]){ //If it is checked
				
				$scope.addedskillids.push(sid);
				
				index = $scope.removedskillids.indexOf(sid)
				if(index > -1){
					$scope.removedskillids.splice(index, 1);
				}
				
			
			}else{
				
				$scope.removedskillids.push(sid);
				
				index = $scope.addedskillids.indexOf(sid)
				if(index > -1){
					$scope.addedskillids.splice(index, 1);
				}
			}
			
			console.log($scope.selectedskills);
			console.log($scope.addedskillids);
			console.log($scope.removedskillids);
		}
	
	
	$scope.updateSkillsEmployee = function(){
		var id = $routeParams.id;
		var addsuccessflag = false
		var removesuccessflag = false
		var addcall,removecall;
		
		if($scope.addedskillids != null ){
			addcall = $http.put('http://localhost:8080/user/employee/'+id+'/addskills', $scope.addedskillids);
		}
		
		if($scope.removedskillids != null ){
			removecall = $http.put('http://localhost:8080/user/employee/'+id+'/removeskills', $scope.removedskillids);	
		}
		
		$q.all([removecall, addcall]).then(function(arrayOfResults) {
			window.location.href='#!/employee/details/'+id;
		})
		
	}
	
	$scope.removeSkillsEmployee = function(skillid) {
		var empid = $routeParams.id;
		
		
		$http.put('http://localhost:8080/user/employee/'+empid+'/removeskills', [skillid]).then(successCallback, errorCallback);
		
		
		function successCallback(response){
			window.location.reload(true)
			//window.location.href='#!/employee/details/'+empid;
			console.log('#/employee/details/'+empid)
		}
		function errorCallback(error){
			console.log('EmployeesController error'+error);
		}
		
	}
	
	$scope.pupdate = function() {
		console.log($scope.selectedProject)
		$scope.allocation.selectedProject = $scope.selectedProject
	}











$scope.initSkills = function() {
	
	$scope.availableskills = []
	

	
	$scope.availableskills =  $scope.allocation.allskills
	
	
	
	
	
	
		
}             





$scope.moveItem = function(item, from, to) {

    console.log('Move item   Item: '+item+' From:: '+from+' To:: '+to);
    //Here from is returned as blank and to as undefined

    var idx=from.indexOf(item);
    if (idx != -1) {
        from.splice(idx, 1);
        to.push(item);      
    }
};

$scope.moveAll = function(from, to) {

    console.log('Move all  From:: '+from+' To:: '+to);
    //Here from is returned as blank and to as undefined

    angular.forEach(from, function(item) {
        to.push(item);
    });
    from.length = 0;
};                

                               

}]);