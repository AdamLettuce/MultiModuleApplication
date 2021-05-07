var hrmApp = angular.module('hrmApp',['ngRoute','Login','Comment','PrivatePerson']);

hrmApp.config(['$routeProvider', 
function($routeProvider) {
	$routeProvider.
		when('/login', {
			templateUrl: 'app/login/login.html',
			controller: 'LoginController'
		}).
		when('/comment', {
			templateUrl: 'app/comment/comment.html',
			controller: 'CommentController'
		}).
		when('/privateperson/add', {
			templateUrl: 'app/private_person/add.html',
			controller: 'PrivatePersonController'
		}).
		otherwise({
			redirectTo:'/login'
		});
}]);
