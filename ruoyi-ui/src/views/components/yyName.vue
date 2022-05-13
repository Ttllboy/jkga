<template>
<div>
  {{yyNames.find(item => item.value == hosId).label}}
</div>
</template>

<script>
export default {
  name: "yyName",
  props:['yyNames','hosId']
}
</script>
<style scoped>

</style>
