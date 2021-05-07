angular.module('Comment', [])
.constant('minimumCommentLength', 3)
.constant('maximumCommentLength', 200)
.constant('minimumUsernameLength', 3)
.controller('CommentController', ['$scope', '$http', 'minimumCommentLength', 'maximumCommentLength', 'minimumUsernameLength', function($scope,$http,minimumCommentLength,maximumCommentLength,minimumUsernameLength) {

	//TODO: why this is being called twice?
	$scope.init = function() {
		//TODO: service
		var findComments = $http.get('http://localhost:8080/comment/findActive');
		findComments.success(function(data) {
			$scope.comments = data;                            		
		});
	}
	$scope.init();

	$scope.deleteComment = function(comment) {
		var deleteComment = $http.delete('http://localhost:8080/comment/deactivate/'+comment.id);
		deleteComment.success(function(data) {
			//TODO: service
			var findComments = $http.get('http://localhost:8080/comment/findActive');
			findComments.success(function(data) {
				$scope.comments = data;                            		
			});
		});
	}

	$scope.comment = { username:"", text: "" };
	$scope.addComment = function() {
		if($scope.commentIsValid()) {
			$scope.comment.createdDate = new Date();
			var addComment = $http.put('http://localhost:8080/comment/add', $scope.comment);
			addComment.success(function(data) {
				//TODO: service
				var findComments = $http.get('http://localhost:8080/comment/findActive');
					findComments.success(function(data) {
					$scope.comments = data;                            		
				});
			});
		}
	}

	$scope.minimumCommentLength = minimumCommentLength;
	$scope.maximumCommentLength = maximumCommentLength;
	$scope.minimumUsernameLength = minimumUsernameLength;

	$scope.commentIsValid = function() {
		return $scope.comment.text.length>=$scope.minimumCommentLength && 
				$scope.comment.text.length<=$scope.maximumCommentLength &&
				$scope.comment.username;
	}

	$scope.commentRemainingCharacters = function() {
		return maximumCommentLength - $scope.comment.text.length; 
	}

	$scope.commentLengthIsValid = function() {
		return $scope.commentRemainingCharacters() >= 0;
	}

	$scope.remainingCharacters = function() {
		return $scope.alterate(['znaków','znak','znaki','znaków'], Math.abs($scope.commentRemainingCharacters()));
	}

	$scope.remaining = function() {
		return $scope.alterate(['Pozostało', 'Pozostał','Pozostały','Pozostało'], Math.abs($scope.commentRemainingCharacters()));

	}

	$scope.alterate = function(alterations, count) {
		if(count===0) {
			return alterations[0];
		} else if(count===1) {
			return alterations[1];
		} else if(count<5) {
			return alterations[2];
		} else {
			return alterations[3]; 
		}
	}

	$scope.toManyCharacters = function() {
		return $scope.remainingCharacters();
	}

	$scope.commentRemainingCharactersText = function() {
		return $scope.remaining() + ' ' + $scope.commentRemainingCharacters() + ' ' + $scope.remainingCharacters() + '.';
	}

	$scope.commentIsToLongText = function() {
		return 'Komentarz jest za długi o ' + (-$scope.commentRemainingCharacters()) + ' ' + $scope.toManyCharacters() + '.';
	}

	angular.element(document).ready(function() {
		var validationRules = {
        userName: {
          identifier  : 'username',
          rules: [
            {
              type   : 'empty',
              prompt : 'Wprowadź proszę swój pseudonim'
            },
            {
              type  : 'length[' + $scope.minimumUsernameLength + ']',
              prompt : 'Pseudonim musi zawierać co najmniej ' + $scope.minimumUsernameLength + ' znaki'
            }            
          ]
        },
        commentText: {
          identifier  : 'commentText',
          rules: [
            {
              type   : 'empty',
              prompt : 'Wprowadź proszę treść komentarza'
            },
            {
              type  : 'length['+$scope.minimumCommentLength+']',
              prompt : 'Komentarz musi zawierać co najmniej ' + $scope.minimumCommentLength + ' znaki'
            }
          ]
        }
      }
    ;

    $('.ui.form')
      .form(validationRules, {
        inline: true,
        on: 'blur'
      });
	});

}]);
