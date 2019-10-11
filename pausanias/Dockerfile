FROM node:10.16.3-alpine AS development

RUN mkdir /services/pausanias \
 && chown node:node /services/pausanias

USER node

WORKDIR /services/pausanias

COPY --chown=node:node package.json package-lock.json ./

RUN npm install --quiet

FROM node:10.16.3-alpine AS production

USER node

WORKDIR /services/pausanias

COPY --from=development --chown=root:root /services/pausanias/node_modules ./node_modules

COPY . .

CMD ["node", "index.js"]
