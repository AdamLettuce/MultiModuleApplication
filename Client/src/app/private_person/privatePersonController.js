angular.module('PrivatePerson', [])
.constant('minimumNameLength', 2)
.constant('maximumNameLength', 20)
.directive('pickADate', function () {
	return {
		restrict: "A",
scope: {
	pickADate: '=',
minDate: '=',
maxDate: '=',
pickADateOptions: '='
},
link: function (scope, element, attrs) {
	      var options = $.extend(scope.pickADateOptions || {}, {
		      onSet: function (e) {
				     if (scope.$$phase || scope.$root.$$phase) // we are coming from $watch or link setup
		      return;
	      var select = element.pickadate('picker').get('select'); // selected date
	      scope.$apply(function () {
		      if (e.hasOwnProperty('clear')) {
			      scope.pickADate = null;
			      return;
		      }
		       if (!scope.pickADate) scope.pickADate = new Date();
	      scope.pickADate.setYear(select.obj.getFullYear());
	      // Interesting: getYear returns only since 1900. Use getFullYear instead.
	      // It took me half a day to figure that our. Ironically setYear()
	      // (not setFullYear, duh) accepts the actual year A.D.
	      // So as I got the $#%^ 114 and set it, guess what, I was transported to ancient Rome 114 A.D.
	      // That's it I'm done being a programmer, I'd rather go serve Emperor Trajan as a sex slave.
	      scope.pickADate.setMonth(select.obj.getMonth());
	      scope.pickADate.setDate(select.obj.getDate());
	      });
			     },
		      onClose: function () {
				       element.blur();
			       }
	      });
	      element.pickadate(options);
	      function updateValue(newValue) {
		      if (newValue) {
			      scope.pickADate = (newValue instanceof Date) ? newValue : new Date(newValue);
			      // needs to be in milliseconds
			      element.pickadate('picker').set('select', scope.pickADate.getTime());
		      } else {
			      element.pickadate('picker').clear();
			      scope.pickADate = null;
		      }
	      }
	      updateValue(scope.pickADate);
	      element.pickadate('picker').set('min', scope.minDate ? scope.minDate : false);
	      element.pickadate('picker').set('max', scope.maxDate ? scope.maxDate : false);
	      scope.$watch('pickADate', function (newValue, oldValue) {
		      if (newValue == oldValue)
		      return;
	      updateValue(newValue);
	      }, true);
	      scope.$watch('minDate', function (newValue, oldValue) {
		      element.pickadate('picker').set('min', newValue ? newValue : false);
	      }, true);
	      scope.$watch('maxDate', function (newValue, oldValue) {
		      element.pickadate('picker').set('max', newValue ? newValue : false);
	      }, true);
      }
};
})
.controller('PrivatePersonController', ['$scope', '$http', 'minimumNameLength', 'maximumNameLength', function($scope,$http,minimumNameLength,maximumNameLength) {

	$scope.init = function() {

	}
	$scope.init();


	$scope.privatePersonIsValid = function() {
		return true;
	}

	$scope.addPrivatePerson = function() {
		var addPrivatePerson = $http.put('http://localhost:8080/private_person/add', $scope.privatePerson);
	}

	$scope.privatePersonIsValid = true;	
	$scope.minimumNameLength = minimumNameLength;
	$scope.maximumNameLength = maximumNameLength;

	$scope.privatePerson = {firstName:"Jan",secondName:"Stefan",lastName:"Kowalski",dateOfBirth:"",sex:"",addresses:[{postalCode:"1",city:"1",street:"1"}],email:"e@mail.com"};	



	angular.element(document).ready(function() {
		var validationRules = {
			firstname: {
					   identifier  : 'first-name',
		rules: [
	{
		type   : 'empty',
		prompt : 'Wprowadź proszę pierwsze imię'
	},
		{
			type  : 'length[' + $scope.minimumNameLength + ']',
		prompt : 'Pierwsze imię musi zawierać co najmniej ' + $scope.minimumNameLength + ' znaki'
		},
		{
			type  : 'maxLength[' + $scope.maximumNameLength + ']',
		prompt : 'Pierwsze imię może zawierać co najwyżej ' + $scope.maximumNameLength + ' znaków'
		}             
	]
				   },
		secondname: {
				    identifier  : 'second-name',
				    rules: [
				    {
					    type   : 'empty',
					    prompt : 'Wprowadź proszę drugie imię'
				    },
				    {
					    type  : 'length['+$scope.minimumNameLength+']',
					    prompt : 'Drugie imię musi zawierać co najmniej ' + $scope.minimumNameLength + ' znaki'
				    },
				    {
					    type  : 'maxLength[' + $scope.maximumNameLength + ']',
					    prompt : 'Drugie imię może zawierać co najwyżej ' + $scope.maximumNameLength + ' znaków'
				    } 
	]
			    },
		surnname: {
				  identifier  : 'surname',
				  rules: [
				  {
					  type   : 'empty',
					  prompt : 'Wprowadź nazwisko'
				  },
				  {
					  type  : 'length['+$scope.minimumNameLength+']',
					  prompt : 'Nazwisko musi zawierać co najmniej ' + $scope.minimumNameLength + ' znaki'
				  },
				  {
					  type  : 'maxLength[' + $scope.maximumNameLength + ']',
					  prompt : 'Nazwisko może zawierać co najwyżej ' + $scope.maximumNameLength + ' znaków'
				  } 
				  ]        	
			  },
		gender: {
				identifier : 'gender',
				rules: [
				{
					type : 'empty',
					prompt : 'Wybierz płeć'
				}
				]
			},
		dateofbirth: {
				     identifier  : 'date-of-birth',
				     rules: [
				     {
					     type   : 'empty',
					     prompt : 'Wprowadź datę urodzenia'
				     } 
				     ]        	
			     },
		postalcode: { 
				identifier: 'postal-code',
				rules: [
					{
						type : 'empty',
						prompt : 'Wprowadź kod pocztowy'
					}				
				]
				},
		city: { 
				identifier: 'city',
				rules: [
					{
						type : 'empty',
						prompt : 'Wprowadź nazwę miasta'
					}				
				]
				},	
		street: { 
				identifier: 'street',
				rules: [
					{
						type : 'empty',
						prompt : 'Wprowadź nazwę ulicy'
					}				
				]
				},			
		email: {
			       identifier : 'e-mail',
			       rules: [
			       {
				       type : 'empty',
				       prompt : 'Wprowadź adres e-mail'
			       },
			       {
				       type: 'email',
				       prompt : 'Wprowadź poprawny adres e-mail'
			       }
			       ]
		       }

		}
		;

		$('.ui.dropdown').dropdown();

		$('.ui.accordion').accordion();

		$('.ui.form')
			.form(validationRules, {
				inline: true,
				on: 'blur'
			});

   		$scope.select = function(container) {
        	$scope.privatePerson.sex = container;        
    	};

	});

}]);
