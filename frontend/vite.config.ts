import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()]
  // server: {
  //   proxy: {
  //     '/api': {
  //       target: 'https://j9b209.p.ssafy.io',
  //       changeOrigin: true
  //       // rewrite: (path) => path.replace(/^\/api/, '')
  //     }
  //   }
  // }
});
