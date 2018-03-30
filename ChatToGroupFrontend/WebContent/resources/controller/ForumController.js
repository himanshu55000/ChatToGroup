app.controller('ForumController',function(ForumService,$scope,$rootScope,$location,$route,$routeParams){
	$scope.forum={}
	$scope.forums=[]
	$scope.commentLogin=function(){
			$rootScope.interceptURL=$location.path();
			$location.path('/login');
	}
	var x;
	$("#myFile").change(function(){
			x=this.files[0].size;
	});
	$scope.addForum=function(){		
			if(x>307200){
				alert("Image size must be under 300KB !!!!!");
			return false;	
			}
				else{
					ForumService.addForum($scope.forum).then(function(response){
						$("#file-upload-button").trigger('click');
						$location.path('/viewProfile/'+$rootScope.currentUser.username);
						alert("Forum added successfully!!!");
					},function(response){
						$('html,body').scrollTop(0);
						console.log(response.status);
						if(response.data.code==5){
							$scope.message="login first!!!";
							$location.path('/login');
						}
						else{		
							alert("Internal Server Error!!!");
						}
					});
				
		}
	}
	
	if($location.path().substring(0,13)=="/updateForum/"){
		console.log("get called");
		var id=$routeParams.id;
		ForumService.getForumById(id).then(function(response){
			$scope.forum=response.data;
			if($scope.forum.forum_title==undefined)
				$location.path('/404Error');
		},function(response){
			alert("Internal Server Error!!!");
		})
	}

	if($location.path().substring(0,11)=="/viewForum/"){
		console.log("get called");
		var id=$routeParams.id;
		ForumService.getForumById(id).then(function(response){
			$scope.forum=response.data;
			if($scope.forum.forum_title==undefined)
				$location.path('/404Error');
			ForumService.getAllForumComment(id).then(function(response){
				$scope.allForumComment=response.data;
			},function(response){
				console.log(response.status);
			})	
			
		},function(response){
			alert("Internal Server Error!!!");
		})
	}
	
	if($location.path()=='/forums'){
		ForumService.getAllForum().then(function(response){
			console.log(response.data);
			$scope.forums=response.data;
		},function(response){
			alert("Internal Server Error!!!");
		})
	}
		
		$scope.deleteForum=function(id){		
		ForumService.deleteForum(id).then(function(response){
			alert("Forum deleted successfully!!!");
			$route.reload();
		},function(response){
			$('html,body').scrollTop(0);
			console.log(response.status);
			if(response.data.code==6){
				$scope.message=response.data.message;
				$location.path('/403Error');
		}
			else if(response.data.code==5){
				$scope.message="login first!!!";
				$location.path('/login');
			}
			else{		
				alert("Internal Server Error!!!");
				$route.reload();
				}
		});
	}
	
	$scope.addForumComment=function(){		
		$scope.forumComment.forum=$scope.forum;
		ForumService.addForumComment($scope.forumComment).then(function(response){
			$scope.forumComment.commentText='';
			ForumService.getAllForumComment($scope.forum.forum_id).then(function(response){
				$scope.allForumComment=response.data;
			},function(response){
				console.log(response.status);
			})
		},function(response){
			console.log(response.status);
			if(response.data.code==5){
				$location.path('/login');
			}
			else{		
				alert("Internal Server Error!!!");
			}
		});
	}
	
	
})