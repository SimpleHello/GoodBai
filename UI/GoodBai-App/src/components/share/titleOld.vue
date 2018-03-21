<template>
	<div class="loanBanner">
		<p><span class="tit">{{tit}}</span></p>
		<span class="back" @click="$router.push('/share')"></span>
	</div>
</template>
<script>
	export default {
		props:['tit','des']
	}
</script>