(ns pinkgorilla.notebook-ui.app-bundel.app
  (:require
   [taoensso.timbre :as timbre :refer [info]]
   [re-frame.core :as rf]
   [webly.user.app.app :refer [webly-run!]]
   ; notebook
   [pinkgorilla.notebook-ui.app.app]
   ;notebook-bundel
   [pinkgorilla.notebook-ui.app-bundel.routes :refer [routes-api routes-app]]
   [pinkgorilla.notebook-ui.app-bundel.pages.about]
   [pinkgorilla.notebook-ui.app-bundel.pages.gorilla-ui]
   ; goldly
   [goldly.app]
   ;gorilla-ui
   [pinkgorilla.ui.default-renderer]
   [pinkgorilla.ui.css :as gorilla-ui]
   [example.example :refer [examples]]
   ;gorilla-plot
   ;[pinkgorilla.gorilla-plot.pinkie]
   ))

(defn ^:export start []
  (webly-run! routes-api routes-app))

(rf/reg-event-db
 :webly/before-load
 (fn [db [_]]
   (info "gorilla-notebook reload..")
   (reset! examples {})
   db))

(rf/reg-event-db
 :notebook/start
 (fn [db [_]]
   (rf/dispatch [:ga/event {:category "notebook" :action "started" :label 77 :value 13}])
   (info "notebook-init")
   (rf/dispatch [:notebook/init [:webly/status :running]])
   (info "gorilla-ui css init")
   (rf/dispatch [:css/add-components gorilla-ui/components gorilla-ui/config])

   db))