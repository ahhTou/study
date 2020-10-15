const path = require('path') // 引入path模块
function resolve(dir) {
    return path.join(__dirname, dir) //path.join(__dirname)设置绝对路径
}

module.exports = {
    chainWebpack: config => {
        config.resolve.alias
            .set('src', resolve('./src'))
            .set('components', resolve('./src/components'))
            .set('views', resolve('src/views'))
            .set('assets', resolve('src/assets'))
            .set('network', resolve('src/network'))
        //set第一个参数：设置的别名，第二个参数：设置的路径
    },

    devServer: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:9000/',
                // target: 'http://39.99.154.145:9000/',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/'
                }
            },
            '/search': {
                target: 'http://127.0.0.1:9001/',
                changeOrigin: true,
                pathRewrite: {
                    '^/search': '/'
                }
            },
        }
    },
    css: {
        loaderOptions: {
            // 配置导入全局 scss
            sass: {
                additionalData: `
                  @import 'src/assets/css/style.scss';
                  @import 'src/assets/css/base.scss';
                  @import 'src/assets/css/fade.scss';
            `
            }
        }
    },

    publicPath: './',
    outputDir: 'hello',

    pluginOptions: {
        'style-resources-loader': {
            preProcessor: 'scss',
            patterns: []
        }
    }
}
