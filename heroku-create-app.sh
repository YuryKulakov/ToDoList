#!/usr/bin/env sh

heroku apps:create todolist
heroku addons:create heroku-postgresql:hobby-dev --app todolist