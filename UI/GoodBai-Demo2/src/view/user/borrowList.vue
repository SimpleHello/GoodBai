<template>
	<div class="container">
		<topComponent title='我的借款2' :showLeft='false'>
			<span class="back" @click="$router.push('/user')" slot="left">返回11</span>
		</topComponent>
		<router-view></router-view>
	</div>
</template>