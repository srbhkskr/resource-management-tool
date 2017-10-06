var myApp = angular.module('myApp');

myApp.controller('SkillsController',['$scope', '$http', '$location', '$routeParams', 'Allocation', function($scope, $http, $location, $routeParams,Allocation){
	console.log('SkillsController loaded')
	
	$scope.allocation = Allocation;

	$scope.getSkills = function(){
		$http.get('http://localhost:8080/user/skills').then(successCallback, errorCallback);

		function successCallback(response){
			$scope.skills = response.data;
			$scope.allocation.allskills = $scope.skills;
			
		}
		function errorCallback(error){
			console.log('SkillsController error'+error);
		}
	}
	
	$scope.models = {
	        selected: null,
	        skill_lists: {"All_skills": []}
	    };
	
	$scope.getSkill = function(){
		var id = $routeParams.id
		console.log('Skill Id :'+id)
		$http.get('http://localhost:8080/user/skill/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			$scope.skill = response.data;
		}
		function errorCallback(error){
			console.log('SkillsController error'+error);
		}
		
	}
	
	$scope.registerSkill = function(){
		
		$http.post('http://localhost:8080/user/skill/',$scope.skill).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/skills';
		}
		function errorCallback(error){
			console.log('SkillsController error'+error);
		}
	}
	
	$scope.updateSkill = function(){
		//var id = $routeParams.id
		$http.put('http://localhost:8080/user/skill/',$scope.skill).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/skills';
		}
		function errorCallback(error){
			console.log('SkillsController error'+error);
		}
	}
	
	$scope.deleteSkill= function(id){
		$http.delete('http://localhost:8080/user/skill/'+id).then(successCallback, errorCallback);

		function successCallback(response){
			window.location.href='#!/skills';
		}
		function errorCallback(error){
			console.log('SkillsController error'+error);
		}
	}
}
]
);