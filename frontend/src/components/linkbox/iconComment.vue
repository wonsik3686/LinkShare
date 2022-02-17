<template>
  <div>
    <router-link class="text-decoration-none" :to="`/${user.email}/linkbox/${linkbox.id}`">
      <v-icon class="mr-1">
        mdi-comment
      </v-icon>
    </router-link>
    <span class="subheading mr-2">{{ linkbox.commentCount }}</span>
  </div>
</template>

<script>
export default {
  props: ['linkbox', 'user'],
}
</script>

<style>

</style>