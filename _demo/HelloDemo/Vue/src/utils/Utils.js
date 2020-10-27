const path = require('path') // 引入path模块

export function isEmpty(obj) {
    return (obj === undefined || obj === '' || obj === null)
}

export function objCopy(source, target) {
    for (let k in source) {
        if (source.hasOwnProperty(k)) {
            let value = source[k]
            if (target.hasOwnProperty(k)) {
                target[k] = value
            }
        }
    }
}

export function pathJoin(prefix, suffix) {
    prefix += '/'
    suffix = '/' + suffix
    prefix = prefix.replaceAll('\\', '/').replaceAll('//', '/')
    suffix = suffix.replaceAll('\\', '/')
    let res = prefix + suffix
    res = res.replaceAll('//', '/').replace('/', '//')

    return res
}
