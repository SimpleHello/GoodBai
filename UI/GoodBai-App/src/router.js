import login from './components/login';

export default [
	{ path: '/', component : login },
	{ path: '*', component : resolve => require(['./view/other/error'], resolve)}	
]
