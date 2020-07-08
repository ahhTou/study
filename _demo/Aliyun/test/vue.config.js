module.exports = {
    publicPath: './',
    outputDir: 'test',
    devServer: {
        proxy: {
            '/test-api': {
                target: 'http://39.99.154.145:9000',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/test-api': ''
                },
            },
        }
    }
}