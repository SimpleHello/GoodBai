<template>
	<div class="container bgF">
		<topComponent title='欢迎光临' :showLeft='false'></topComponent>
		<h2 class="logoIcon">登录界面</h2>
		<ul class="formCom form-login form-mini">
			<li class="icon-clear">
				<label>
					<span>账号</span>
					<input type="number" placeholder="请输入手机号" v-model.number='name' >
					<i @click='name = ""'></i>
				</label>
			</li>
			<li :class='[{"icon-eye1":showPwd},{"icon-eye2":!showPwd}]'>
				<label>
					<span>密码</span>
					<input type="password" placeholder="请输入6位密码" v-model.trim='pwd' v-if='showPwd' >
					<input type="text" placeholder="请输入6位密码" v-model.trim='pwd' v-if='!showPwd'>
					<i @click='showPwd = !showPwd'></i>
				</label>
			</li>
			<!--<li>-->
				<!--<canvasCode @codeHasChange='sendCode'></canvasCode>-->
			<!--</li>-->
		</ul>
		<div class="btnWarp">
			<span class="subBtn"	@click='logIn'>登录</span>
		</div>
		<transition name="scale">
			<dialogPopup :class='diglogClass' :msg='diglogCont' v-show='diglogShow'></dialogPopup>
		</transition>
	</div>
</template>
<script type="text/ecmascript-6">
	import ajax from '../config/ajax.js';
	import {ymzs} from '../config/auth.js';
	export default {
	    data () {
	        return {
	        	name:'',
	        	pwd:'',
	        	code:'',			
	        	showPwd:true,		//开关--密码可见
	        	diglogShow:false,	//开关--显示diglog组件,
	        	canvas:{}			//存放canvas DOM节点
	        }
	    },
	    methods :{
	        logIn () {

//	        	//先做一些简单的判断再提交ajax
	        	if( ""==this.name ||this.name ==null )		this.callDialog('帐号不正确');
	        	else if( ""==this.pwd ||this.pwd ==null)		this.callDialog('密码不正确');
//	        	else if( this.code.toUpperCase() !== this.canvasCode.codeNums.toUpperCase() )	this.callDialog('验证码不正确');
	        	else{
					let param = new Object();
					param.name = this.name;
					param.password = this.pwd;
					ajax.post('/user/login.do',param).then(data => {
						if(data.error<0){
						this.callDialog('获取列表失败');
						return false;
					}else{
						this.leftNav= data.rows;
						ymzs.nav = data.rows;
						this.$router.push("/share")
						return true
					}
				}).catch(function (err) {
						return err;
					});
	        	}
	        },
	        goCancel () {
	        	//根据情景不一样，跳转的页面也会不同，暂定跳到我的贷款首页
	        	//...省略
	            this.$router.push('/loan');
	        },
	        sendCode(val){
	        	this.code = val;
	        }
	    }
	}
</script>
<style>

</style>