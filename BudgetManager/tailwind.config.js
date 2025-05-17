/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts,js}",
    "./index.html"
  ],
  theme: {
    extend: {
      colors: {
        'income': '#38c172',
        'expense': '#e3342f',
        'saving': '#3490dc',
        'warning': '#f6993f',
        'neutral': '#718096',
      }
    },
  },
  plugins: [],
}
