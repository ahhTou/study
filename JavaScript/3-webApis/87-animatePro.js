let move = {
  step: '0px',
}
var _callback = null
var $obj
function animate($obj1, target, callback, deadline = Boolean()) {
  if (deadline) {
    _callback = callback
  }
  $obj = $obj1
  clearInterval($obj.timer)
  $obj.timer = setInterval(() => {
    let step = (target - $obj.offsetLeft) / 10
    step = step > 0 ? Math.ceil(step) : Math.floor(step)
    if ($obj.offsetLeft === target) {
      clearInterval($obj.timer)
      if (callback) if (!deadline) callback()
    } else {
      move.step = $obj.offsetLeft + step
    }
  }, 30)
}
Object.defineProperty(move, 'step', {
  set: function (value) {
    $obj.style.left = value + 'px'
    if (_callback) {
      _callback(value, $obj.timer)
    }
  },
})
