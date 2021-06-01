(ns pinkgorilla.notebook-ui.app.pages.about
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [webly.web.handler :refer [reagent-page]]
   [pinkgorilla.notebook-ui.app.site :as site]))

(defn credit [user txt]
  [:li
   [:span
    [:a.w-16 {:href (str "https://github.com/" user)} user]
    [:span.ml-5 txt]]])

(defn action [{:keys [on-click href]} text]
  [:div.w-48.h-24.p-5.border-2.border-solid.border-gray-500.rounded.text-center.text-lg.cursor-pointer.bg-pink-100.hover:bg-pink-400
   {:on-click on-click
    :href href}
   (if href
     [:a {:href href} text]
     text)])

(defn block [{:keys [name class]}  & children]
  (into [:div.bg-blue-400.m-5.inline-block
         {:class class}
         [:h1.text-2xl name]]
        children))

(defn action-box []
  [:div.mt-5
   [:div.flex.flex-column.justify-evenly
    [action {:on-click #(rf/dispatch [:document/new])} "New Notebook"]]])

(defn features-box []
  [:div.bg-yellow-300.mt-5.p-5
   [:h1.text-2xl "Features"]
   [:div.prose
    [:ul.list-disc
     [:li "Notebook with clj kernel"]
     [:li "Many visualization components"]]]])

(defn keybindings-box []
  [:div.bg-yellow-300.mt-5.p-5
   [:h1.text-2xl "Keybindings"]
   [:p "shift enter - eval current cell"]
   [:p "alt-g k - keybinding dialog"]
   [:p "alt-g e - explorer window"]
     ;[:p "alt-g m - main window"]
     ;[:p "alt-g d - docs window"]
     ;[:p "alt-g n - notebook window"]
   [:p "alt-g r - repl info window"]])

(defn credits-box []
  [:div.bg-green-300.mt-5.p-5
   [:h1.text-2xl "Credits"]
   [:div.prose
    [:ul.list-disc
     [credit "JonyEpsilon" "legacy gorilla-repl (with js frontend)"]
     [credit "deas" "port to re-frame, nrepl-relay, build-automation, unit-tests"]
     [credit "awb99" "gorilla-ui, goldly"]
     [credit "mauricioszabo" "goldly sci compiler, nrepl-tooling"]
     [credit "daslu" "notworking and testing, clojisr"]]]])

(defn notebook-about []
  [:div.container.mx-auto.gorilla-markdown
   [:h1.mt-5.text-2xl.italic "Welcome to" [:span.pl-5.text-pink-800.text-3xl.non-italic "PinkGorilla Notebook"]]
   [action-box]
   [:div.flex.flex-wrap
    [features-box]
    [keybindings-box]
    [credits-box]]])

(defmethod reagent-page :notebook/about [{:keys [route-params query-params handler] :as route}]
  [:div
   [site/header]
   [notebook-about]])