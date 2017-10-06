var myApp = angular.module('myApp',['ngRoute']);

myApp.config(function($routeProvider){
	$routeProvider.when('/',{
		controller:'EmployeesController',
		templateUrl: 'views/employees.html'
	})
	.when('/employees',{
		controller:'EmployeesController',
		templateUrl:'views/employees.html'
	})
	.when('/employee/details/:id',{
		controller:'EmployeesController',
		templateUrl:'views/employee_details.html'
	})
	.when('/employee/add',{
		controller:'EmployeesController',
		templateUrl:'views/register_employee.html'
	})
	.when('/employee/update/:id',{
		controller:'EmployeesController',
		templateUrl:'views/update_employee.html'
	})
	.when('/employee/:id/updateskill',{
		controller:'EmployeesController',
		templateUrl:'views/employee_update_skill2.html'
	})
	.when('/projects',{
		controller:'ProjectsController',
		templateUrl:'views/projects.html'
	})
	.when('/project/details/:id',{
		controller:'ProjectsController',
		templateUrl:'views/project_details.html'
	})
	.when('/project/add',{
		controller:'ProjectsController',
		templateUrl:'views/register_project.html'
	})
	.when('/project/update/:id',{
		controller:'ProjectsController',
		templateUrl:'views/update_project.html'
	})
	.when('/project/:id/updateemployees',{
		controller:'ProjectsController',
		templateUrl:'views/project_update_employee.html'
	})
	.when('/project/:id/updateskill',{
		controller:'ProjectsController',
		templateUrl:'views/project_update_skill.html'
	})
	.when('/employee/:id/addworkallocation',{
		controller:'WorkAllocationsController',
		templateUrl:'views/register_workallocation.html'
	})
	.when('/workallocation/update/:id',{
		controller:'WorkAllocationsController',
		templateUrl:'views/update_workallocation.html'
	})
	.otherwise({
		redirectTo:'/'
	})
});


myApp.service('Allocation', function () {
    return {};
})