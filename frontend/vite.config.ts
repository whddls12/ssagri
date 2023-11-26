import { createRequire } from 'node:module';
const require = createRequire(import.meta.url);
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
// @ts-ignore
import nodePolyfills from 'vite-plugin-node-stdlib-browser';
import ckeditor5 from '@ckeditor/vite-plugin-ckeditor5';
import svgr from '@svgr/rollup';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    svgr(),
    nodePolyfills(),
    ckeditor5({ theme: require.resolve('@ckeditor/ckeditor5-theme-lark') })
  ],
  server: {
    hmr: {
      overlay: false
    }
    // build: {
    //   rollupOptions: {
    //     external: ['sockjs-client', 'stompjs', '@stomp/stompjs', 'socket.io']
    //   }
    // },
    // proxy: {
    //   '/api': {
    //     // target: 'https://j9b209.p.ssafy.io',
    //     target: 'http://localhost:5000',
    //     changeOrigin: true
    //     // rewrite: (path) => path.replace(/^\/api/, '')
    //   }
    // '/ws': {
    //   // target: 'ws://j9b209.p.ssafy.io',
    //   target: 'ws://localhost:5000',
    //   changeOrigin: true
    //   // rewrite: (path) => path.replace(/^\/api/, '')
    // }
    // }
  }
});
