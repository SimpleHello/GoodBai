<template>
	<div class="container">
		<topComponent title='当前最新统计' :showLeft='false'>
			<span class="back" @click="$router.push('/data/index')" slot="left">返回</span>
		</topComponent>
		<router-view></router-view>
	</div>
</template>