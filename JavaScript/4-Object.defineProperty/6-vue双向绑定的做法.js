function Observer(data) {
  this.data = data;
  this.walk(data);
}

var p = Observer.prototype;

var arrayProto = Array.prototype;

var arrayMethods = Object.create(arrayProto);

[
  'push',
  'pop',
  'shift',
  'unshift',
  'splice',
  'sort',
  'reverse'
].forEach(function(method) {
  // 使用 Object.defineProperty 进行监听
  Object.defineProperty(arrayMethods, method, {
    value: function testValue() {
      console.log('数组被访问到了');
      const original = arrayProto[method];
      // 使类数组变成一个真正的数组
      const args = Array.from(arguments);
      original.apply(this, args);
    }
  });
});

p.walk = function(obj) {
  let value;
  for (let key in obj) {
    // 使用 hasOwnProperty 判断对象本身是否有该属性
    if (obj.hasOwnProperty(key)) {
      value = obj[key];
      // 递归调用，循环所有的对象
      if (typeof value === 'object') {
        // 并且该值是一个数组的话
        if (Array.isArray(value)) {
          const augment = value.__proto__ ? protoAugment : copyAugment;
          augment(value, arrayMethods, key);
          observeArray(value);
        }
        /* 
         如果是对象的话，递归调用该对象，递归完成后，会有属性名和值，然后对
         该属性名和值使用 Object.defindProperty 进行监听即可
         */
        new Observer(value);
      }
      this.convert(key, value);
    }
  }
}

p.convert = function(key, value) {
  Object.defineProperty(this.data, key, {
    enumerable: true,
    configurable: true,
    get: function() {
      console.log(key + '被访问到了');
      return value;
    },
    set: function(newVal) {
      console.log(key + '被重新设置值了' + '=' + newVal);
      // 如果新值和旧值相同的话，直接返回
      if (newVal === value) return;
      value = newVal;
    }
  });
}

function observeArray(items) {
  for (let i = 0, l = items.length; i < l; i++) {
    observer(items[i]);
  }
}

function observer(value) {
  if (typeof value !== 'object') return;
  let ob = new Observer(value);
  return ob;
}

function def (obj, key, val) {
  Object.defineProperty(obj, key, {
    value: val,
    enumerable: true,
    writable: true,
    configurable: true
  })
}

// 兼容不支持 __proto__的方法
function protoAugment(target, src) {
  target.__proto__ = src;
}

// 不支持 __proto__的直接修改先关的属性方法
function copyAugment(target, src, keys) {
  for (let i = 0, l = keys.length; i < l; i++) {
    const key = keys[i];
    def(target, key, src[key]);
  }
}


// 下面是测试数据

var data = {
  testA: {
    say: function() {
      console.log('kongzhi');
    }
  },
  xxx: [{'a': 'b'}, 11, 22]
};

var test = new Observer(data);

console.log(test); 

data.xxx.push(33);
