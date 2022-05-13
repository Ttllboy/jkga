<template>
  <div>
    {{buildingNames.find(item => item.value == buildingId).label}}
  </div>
</template>

<script>
export default {
  name: "buildingName",
  props: ['buildingNames','buildingId']
}
</script>

<style scoped>

</style>
