import login from './components/login';
import shareIndex from './view/user/index';

export default [
	{ path: '/', component : login },
	{path: '/share', component : shareIndex },
	{ path: '/share/detail', component : resolve => require(['./view/user/shareList'], resolve) , children:[
		{ path:'/' , component: resolve => require(['./view/user/shareDetailList'], resolve) },
		{ path:'ga/:id' , component: resolve => require(['./view/user/shareReportList'], resolve) },
		{path:'add' , component: resolve => require(['./view/user/shareDetailAdd'], resolve)},
		{path:'chart/:code/:name' , component: resolve => require(['./view/user/shareChart'], resolve)},
		{path:'info/:code/:name' , component: resolve => require(['./view/user/shareDetailinfo'], resolve)},
	]},
	{ path: '*', component : resolve => require(['./view/other/error'], resolve)}	
]
