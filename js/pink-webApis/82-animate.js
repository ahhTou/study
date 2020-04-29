function animate($obj, target, callback) {
  clearInterval($obj.timer)
  $obj.timer = setInterval(() => {
    let step = (target - $obj.offsetLeft) / 10
    step = step > 0 ? Math.ceil(step) : Math.floor(step)
    if ($obj.offsetLeft === target) {
      clearInterval($obj.timer)
      if (callback) {
        callback()
      }
    } else {
      $obj.style.left = $obj.offsetLeft + step + 'px'
    }
  }, 30)
}
