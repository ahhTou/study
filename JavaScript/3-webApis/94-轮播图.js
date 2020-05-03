window.addEventListener('load', () => {
  let $focus = document.querySelector('.focus')
  let $ul = $focus.querySelector('ul')
  let $ol = document.querySelector('ol')
  // 获取 focus 的宽度
  let w = $focus.offsetWidth
  // 定时器自动轮播图片
  let index = 0
  function toDO() {
    index++
    let translateX = -index * w
    $ul.style.transition = 'all .3s'
    $ul.style.transform = 'translate(' + translateX + 'px)'
  }
  let timer = setInterval(() => {
    toDO()
  }, 2000)
  // 等待过渡完成之后 再去判定 监听过度完成的事件
  $ul.addEventListener('transitionend', function () {
    // 无缝滚动
    if (index >= 4) {
      index = 0
      $ul.style.transition = 'none'
      let translateX = -index * w
      $ul.style.transform = 'translateX(' + translateX + 'px)'
    } else if (index < 0) {
      index = 3
      $ul.style.transition = 'none'
      let translateX = -index * w
      $ul.style.transform = 'translateX(' + translateX + 'px)'
    }
    $ol.querySelector('.current').classList.remove('current')
    $ol.children[index].classList.add('current')
  })

  let startX = 0
  let moveX = 0
  let flag = false
  // 手指滑动轮播图
  $ul.addEventListener('touchstart', function (e) {
    clearInterval(timer)
    startX = e.targetTouches[0].clientX
  })
  $ul.addEventListener('touchmove', function (e) {
    e.preventDefault()
    flag = true
    moveX = e.targetTouches[0].clientX - startX
    if (moveX > 0 && index === 0) {
      console.log('123')
      index = 4
      let translateX = -index * w
      $ul.style.transition = 'none'
      $ul.style.transform = 'translateX(' + translateX + 'px)'
    } else {
      console.log('正常的移动')
      let translateX = -index * w + moveX
      $ul.style.transition = 'none'
      $ul.style.transform = 'translateX(' + translateX + 'px)'
    }
  })
  $ul.addEventListener('touchend', function (e) {
    if (flag) {
      if (Math.abs(moveX) > 50) {
        if (moveX > 0) {
          index--
        }
        if (moveX < 0) {
          index++
        }
        let translateX = -index * w
        $ul.style.transition = 'all .3s'
        $ul.style.transform = 'translateX(' + translateX + 'px)'
      } else {
        let translateX = -index * w
        $ul.style.transition = 'all .3s'
        $ul.style.transform = 'translateX(' + translateX + 'px)'
      }
      flag = false
    }
    clearInterval(timer)
    timer = setInterval(() => {
      toDO()
    }, 2000)
  })
})
