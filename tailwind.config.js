/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  content: ['./src/main/resources/templates/*.html',
    './src/main/resources/templates/fragments/*.html',
    './src/**/*.js',
    './node_modules/flowbite/**/*.js',
    './node_modules/tw-elements/dist/js/**/*.js'],
  purge: ["./src/**/*.html", "./src/**/*. js"],
  darkMode: 'class',
  theme: {
    fontFamily: {
      'trace': ['Trace'],
      'jetbrains': ['JetBrains Mono', 'monospace'],
      'opensans': ['"Open Sans"', 'sans-serif'],
      'audiowide': ['Audiowide', 'cursive']
    }
  },
  variants: {
    extend: {},
  },
}
