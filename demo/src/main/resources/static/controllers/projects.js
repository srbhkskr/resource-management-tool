var myApp = angular.module('myApp');

myApp.controller('ProjectsController',['$scope', '$http', '$location', '$routeParams','$q', function($scope, $http, $location, $routeParams, $q){
	console.log('ProjectsController loaded')
	
	$scope.selectedskills = []
	$scope.selectedEmployees = []
	
	$scope.billing_types = ["Intenal","External"];
	
	$scope.getProjects = function(){
		$http.get('http://localhost:8080/user/projects').then(successCallback, errorCallback);

		function successCallback(response){
			$scope.projects = response.data;
		}
		function errorCallback(error){
			console.log('ProjectsController error'+error);
		}
	}
	
	
	$scope.getProject = function(){
		var id = $routeParams.id
		console.log('Project Id :'+id)
		$http.get('http://localhost:8080/user/project/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			$scope.project = response.data;
			
			$scope.project.skills.forEach(function(skill, index) {
				$scope.selectedskills[skill.id] = true;
				});
			
			
			$scope.project.employees.forEach(function(employee, index) {
				$scope.selectedEmployees[employee.id] = true;
				});
			
			console.log($scope.project)
		}
		
			
				
			
		
		function errorCallback(error){
			console.log('ProjectsController error'+error);
		}
		
	}
	
	$scope.registerProject = function(){
		
		$http.post('http://localhost:8080/user/project/',$scope.project).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/projects';
		}
		function errorCallback(error){
			console.log('ProjectsController error'+error);
		}
	}
	
	$scope.updateProject = function(){
		//var id = $routeParams.id
		$http.put('http://localhost:8080/user/project/',$scope.project).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/project/details/'+$scope.project.id;
		}
		function errorCallback(error){
			console.log('ProjectsController error'+error);
		}
	}
	
	$scope.deleteProject= function(id){
		$http.delete('http://localhost:8080/user/project/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/projects';
		}
		function errorCallback(error){
			console.log('ProjectsController error'+error);
		}
	}
	
	
	$scope.addedskillids = []
	$scope.removedskillids = []
	
		$scope.stateChanged2 = function (sid) {
			
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
	
	
	$scope.updateSkillsProject = function(){
		var id = $routeParams.id;
		var addsuccessflag = false
		var removesuccessflag = false
		var addcall,removecall;
		
		if($scope.addedskillids != null ){
			addcall = $http.put('http://localhost:8080/user/project/'+id+'/addskills', $scope.addedskillids);
		}
		
		if($scope.removedskillids != null ){
			removecall = $http.put('http://localhost:8080/user/project/'+id+'/removeskills', $scope.removedskillids);	
		}
		
		$q.all([removecall, addcall]).then(function(arrayOfResults) {
			window.location.href='#!/project/details/'+id;
		})
		
	}
	
	$scope.removeSkillsProject = function(projectid,skillid){
		var removecall;
				
		if($scope.removedskillids != null ){
			removecall = $http.put('http://localhost:8080/user/project/'+projectid+'/removeskills', [skillid]);	
			window.location.href='#/project/details/'+projectid;
		}
		
		$q.all([removecall]).then(function(arrayOfResults) {
			window.location.href='#!/project/details/'+projectid;
		})
		
	}
	
	$scope.addedemployeeids = []
	$scope.removedemployeeids = []
	
		$scope.stateChangedEmployee = function (eid) {
			
			if($scope.selectedEmployees[eid]){ //If it is checked
				
				$scope.addedemployeeids.push(eid);
				index = $scope.removedemployeeids.indexOf(eid)
				if(index > -1){
					$scope.removedemployeeids.splice(index, 1);
				}
				
			
			}else{
				
				$scope.removedemployeeids.push(eid);
				
				index = $scope.addedemployeeids.indexOf(eid)
				if(index > -1){
					$scope.addedemployeeids.splice(index, 1);
				}
			}
			
			console.log($scope.selectedEmployees);
			console.log($scope.addedemployeeids);
			console.log($scope.removedemployeeids);
		}
	
	
	$scope.updateEmployeesProject = function(){
		var id = $routeParams.id;
		var addsuccessflag = false
		var removesuccessflag = false
		var addcall,removecall;
		
		if($scope.addedemployeeids != null ){
			addcall = $http.put('http://localhost:8080/user/project/'+id+'/addemployees', $scope.addedemployeeids);
		}
		
		if($scope.removedemployeeids != null ){
			removecall = $http.put('http://localhost:8080/user/project/'+id+'/removeemployees', $scope.removedemployeeids);	
		}
		
		$q.all([removecall, addcall]).then(function(arrayOfResults) {
			window.location.href='#!/project/details/'+id;
		})
		
	}
	
	$scope.removeEmployeesProject = function(projectid,empid){
		
		var removecall;
		
		if($scope.removedemployeeids != null ){
			removecall = $http.put('http://localhost:8080/user/project/'+projectid+'/removeemployees', [empid]);
			window.location.href='#/project/details/'+projectid;
		}
		
		$q.all([removecall]).then(function(arrayOfResults) {
			window.location.href='#!/project/details/'+projectid;
		})
		
	}

}]);