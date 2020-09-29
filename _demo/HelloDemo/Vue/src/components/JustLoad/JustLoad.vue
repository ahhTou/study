<template>
    <div id="justLoadWrapper">
        <loading-animation/>
    </div>
</template>

<script>
import LoadingAnimation from 'components/LoadingAnimation'

export default {
    name: 'JustLoad',
    components: {LoadingAnimation},

}
</script>

<style lang="scss" scoped>
#justLoadWrapper {
    @include flex;
    position: relative;
    font-size: 3rem;
    width: 100%;
    height: 100%;
}
</style>