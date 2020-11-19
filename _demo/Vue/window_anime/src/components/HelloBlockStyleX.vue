<template>
  <div class="a-block-wrapper" :class="wrapperClassName" :style="blockStyle">
    <div class="a-block-icon" :class="blockClassName" @click="toBlur"/>
    <div class="a-block-blur" :class="blurClassName"/>
    <div class="a-block-options" :class="iconClassName">
      <div class="a-block-option">open</div>
      <slot>

      </slot>
      <div class="a-block-option a-block-option-exit" @click="toBlur">exit</div>
    </div>
  </div>
</template>

<script>
import Bus from '@/assets/js/Bus'

export default {
  name: 'HelloBlockStyleX',
  data: function () {
    return {
      blur: 0,
      isFocus: false,
      blockClassName: [],
      wrapperClassName: [],
      blurClassName: [],
      iconSizeWidth: 80,
      iconSizeHeight: 80,
      iconX: 0,
      iconY: 0,
    }
  },
  computed: {
    blockStyle() {
      return {
        '--icon-size-height': this.iconSizeHeight + 'px',
        '--icon-size-width': this.iconSizeWidth + 'px',
        '--blur-status': this.blur,
        '--option-move-x': 0 + 'px',
        '--option-move-y': 85 + 'px',
      }
    },
    iconClassName() {
      let name = 'bigger'
      if (this.blockClassName.isInclude(name)) {
        return ['bigger-option']
      } else return []

    },
  },
  watch: {
    isFocus(val) {
      let name1 = 'bigger'
      let name2 = 'focus'
      let name3 = 'a-block-blur-active'
      if (val) {
        // 开启Focus
        this.blur = 1
        this.blockClassName.push(name1)
        this.wrapperClassName.push(name2)
        this.blurClassName.push(name3)
        this.isFocus = true

      } else {
        // 关闭Focus
        this.blur = 0
        this.blockClassName.remove(name1)
        this.wrapperClassName.remove(name2)
        this.blurClassName.remove(name3)
        this.isFocus = false
      }
      Bus.$emit('bgChange')
    }
  },
  methods: {
    toBlur() {
      this.isFocus = !this.isFocus
    }
  }
}
</script>

<style lang="scss" scoped>

.a-block-wrapper {
  margin: 20px;
  --blur-status: 0;
  --icon-size-height: 80px;
  --icon-size-width: 80px;
  --option-move-x: 0;
  --option-move-y: 85px;
  z-index: 1;
  $oh: 50px;
  $ow: 200px;
  $shadow: 20px 20px 40px rgba(0, 0, 0, .2);
  $rd: 10px;
  $timer: .3s;

  @mixin size() {
    width: var(--icon-size-width);
    height: var(--icon-size-height);
  }
  @include size();
  position: relative;
  cursor: pointer;

  .a-block-icon {
    @include size();
    position: relative;
    border-radius: $rd;
    background: url("~@/assets/img/1_1.jpg") center;
    background-size: 200%;
    box-shadow: $shadow;
    transition: all $timer;
    transform: scale(1);
    transform-origin: top left;
    z-index: 2;

    &:hover {
      transform: scale(1.05);
    }
  }

  .a-block-options {
    z-index: 3;

    pointer-events: none;
    position: absolute;
    top: 0;
    left: 0;
    width: $ow;
    overflow: hidden;
    border-radius: $rd;
    box-shadow: $shadow;
    transition: all $timer;
    transform-origin: top left;
    transform: translate(var(--option-move-x), var(--option-move-y));
    opacity: 0;

    .a-block-option {
      background: rgb(43, 43, 43);
      height: $oh;
      font-weight: bolder;
      display: flex;
      color: rgb(229, 229, 229);
      justify-content: center;
      align-items: center;
      transition: all .3s;
      user-select: none;

      &:hover {
        background: rgb(59, 59, 59);
      }
    }

    .a-block-option-exit {
      &:hover {
        background: rgb(234, 73, 95);
      }
    }
  }

  .bigger {
    transform: scale(1.3);
    z-index: 3;

    &:hover {
      transform: scale(1.3);
    }
  }

  .bigger-option {
    pointer-events: all;
    opacity: 1;
    transform: scale(1.3) translate(var(--option-move-x), var(--option-move-y));
  }

  .a-block-blur {
    position: fixed;
    height: 100vh;
    width: 100vw;
    top: 0;
    left: 0;
    pointer-events: none;
    opacity: var(--blur-status);
    backdrop-filter: blur(10px);
    background: transparent;
    z-index: 1;
    transition: all $timer;
    transform: scale(2);
  }

  .a-block-blur-active {
    pointer-events: all;
  }
}

.focus {
  z-index: 10;
}
</style>
