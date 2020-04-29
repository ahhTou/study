window.addEventListener('load', () => {
  let $right = document.querySelector('.right')
  let $left = document.querySelector('.left')

  let $focus = document.querySelector('.focus')

  let $ol = $focus.querySelector('ol')
  let $ul = $focus.querySelector('ul')
  // 动态生成小圆圈
  for (let i = 0; i < $ul.children.length; i++) {
    let $liSon = document.createElement('li')
    $liSon.dataset.index = i
    $liSon.addEventListener('click', function () {})
    $ol.appendChild($liSon)
  }
  $ol.children[0].className = 'current'
  let current_index = 0

  let imgWidth = $ul.children[0].offsetWidth

  for (let i = 0; i < $ol.children.length; i++) {
    $ol.children[i].addEventListener('click', function (e) {
      for (let a = 0; a < $ol.children.length; a++) {
        $ol.children[a].className = '' // 排他
      }
      e.target.className = 'current'
      current_index = e.target.dataset.index
      animate($ul, -imgWidth * i)
    })
  }

  $left.addEventListener('click', function () {
    add(false)
  })
  $right.addEventListener('click', function () {
    add(true)
  })
  $right.style.display = 'none'
  function add(act) {
    $left.style.display = 'flex'
    $right.style.display = 'flex'
    $ol.children[current_index].className = ''
    act ? current_index++ : current_index--
    if (current_index === $ul.children.length - 1) $right.style.display = 'none'
    if (current_index === 0) $left.style.display = 'none'
    animate($ul, -imgWidth * current_index)
    $ol.children[current_index].className = 'current'
  }

  $focus.addEventListener('mouseenter', function () {
    if (current_index !== $ul.children.length - 1) {
      $right.style.display = 'flex'
    }
    if (current_index !== 0) {
      $left.style.display = 'flex'
    }
  })

  $focus.addEventListener('mouseleave', function () {
    $left.style.display = 'none'
    $right.style.display = 'none'
  })
})
