/**
 * 
 */
app.factory('ForumService',function($http){
	var forumService={};
		
	forumService.addForum=function(forum){
		return $http.post(BASE_URL+"/addForum",forum)
	}
	
	forumService.deleteForum=function(id){
		return $http['delete'](BASE_URL+"/deleteForum/"+id)
	}
	
	forumService.getAllForum=function(){
		return $http.get(BASE_URL+"/getAllForum")
	}
	
	
	forumService.getForumById=function(id){
		return $http.get(BASE_URL+"/getForumById/"+id)
	}
		
	forumService.getAllForumComment=function(id){
		return $http.get(BASE_URL+"/getAllForumComment/"+id)
	}
	
	forumService.addForumComment=function(forumComment){
		return $http.post(BASE_URL+"/addForumComment",forumComment)
	}
	
	return forumService;
})
