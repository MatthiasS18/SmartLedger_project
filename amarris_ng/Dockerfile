# Dockerfile.angular
FROM node:20-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Nginx pour servir Angular
FROM nginx:alpine
COPY --from=build /app/dist/nom-de-ton-app /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]