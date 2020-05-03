window.addEventListener('load', () => {
  let $right = document.querySelector('.right')
  let $left = document.querySelector('.left')

  let $focus = document.querySelector('.focus')

  let $ol = $focus.querySelector('ol')
  let $ul = $focus.querySelector('ul')

  // 动态生成小圆圈
  let length = $ul.children.length
  for (let i = 0; i < length; i++) {
    let $liSon = document.createElement('li')
    $liSon.dataset.index = i
    $liSon.addEventListener('click', function () {})
    $ol.appendChild($liSon)
  }
  $ol.appendChild($ol.children[0])
  $ol.children[0].className = 'current'
  let current_index = 0
  let imgWidth = $ul.children[0].offsetWidth

  // 生成一个copy
  let $copy = $ul.children[0].cloneNode(true)
  $ul.appendChild($copy)
  let copyOK = true

  // 小圈圈响应
  for (let i = 0; i < $ol.children.length; i++) {
    $ol.children[i].addEventListener('click', function (e) {
      setCurrent(i)
      animate($ul, -imgWidth * i)
    })
  }

  // 左右键功能
  $left.addEventListener('click', function () {
    add(false)
  })
  $right.addEventListener('click', function () {
    add(true)
  })

  let timer = setInterval(() => {
    add(true)
  }, 1000)

  //左右键显示隐藏
  $focus.addEventListener('mouseenter', function () {
    $right.style.display = 'flex'
    $left.style.display = 'flex'
    clearInterval(timer)
  })

  $focus.addEventListener('mouseleave', function () {
    $left.style.display = 'none'
    $right.style.display = 'none'
    timer = setInterval(() => {
      add(true)
    }, 3000)
  })

  function add(act) {
    // 判断 current_index 的 值
    act ? current_index++ : current_index--

    // 1.正常区段
    if (current_index < length && current_index >= 0) {
      setCurrent(current_index)
    }

    // 2.切换到copy
    if (current_index === length) {
      setCurrent(0, false)
    }

    // 3.切换到尾
    if (current_index < 0) {
      setCurrent(length - 1, false)
    }

    // 4.临界修正函数
    let deadFun = (value, timer) => {
      if (-value > imgWidth * length && act) {
        setCurrent(1)
        clearInterval(timer)
        $ul.style.left = '0px'
        animate($ul, -imgWidth * 1)
      }
      if (value > 0 && !act) {
        setCurrent(length - 1)
        clearInterval(timer)
        $ul.style.left = -imgWidth * length + 'px'
        animate($ul, -imgWidth * (length - 1))
      }
    }

    animate($ul, -imgWidth * current_index, deadFun, true)
  }

  function setCurrent(num, setIndex = true) {
    for (let a = 0; a < length; a++) {
      $ol.children[a].className = ''
    }
    $ol.children[num].className = 'current'
    if (setIndex) current_index = num
  }
})
