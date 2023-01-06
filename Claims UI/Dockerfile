FROM node:latest as node

RUN mkdir /myapp2
WORKDIR /myapp2
COPY . .
RUN npm install --force
RUN npm run build --prod


#stage 2
FROM nginx:alpine
COPY --from=node /myapp2/dist/claims-americold /usr/share/nginx/html
