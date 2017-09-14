
jQuery(document).ready(function() {
    /*
        Fullscreen background
    */
    $.backstretch("image/1.jpg");
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"]').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    function sys_login(){
	   alert(ctx);
	   var name = $("#form-username").val();
	   var password = $("#form-password").val();
	   if(name.val()==""){
			alert("您输入的用户名为空");
			return;
		}
		if(password==""){
			alert("您输入的密码为空");
			return;
		}	
		var param = {};
		param.userName = name;
		param.password = password;
		$.ajax({
			type:'POST',
			data:param,
			dataType:'json',
			url:ctx+"/login/loginsubmit.do",
			success:function(data){
				if(data.error!=1){
					alert(data.message);
				}else{
					window.location = ctx+"/view/main.jsp";
				}
			}
		}); 
   } 

    
});
