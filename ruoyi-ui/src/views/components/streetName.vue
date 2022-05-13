<template>
  <div>
    {{streetNames.find(item => item.value == streetId).label}}
  </div>
</template>

<script>
export default {
  name: "streetName",
  props: ['streetNames','streetId'],
}

</script>

<style scoped>

</style>


