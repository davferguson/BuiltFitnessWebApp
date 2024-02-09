import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import OnlineProgramsView from '../views/OnlineProgramsView.vue'
import ClassesView from '../views/ClassesView.vue'
import ContactView from '../views/ContactView.vue'
import SignInView from '../views/SignInView.vue'
import MedSearchView from '../views/MedSearchView.vue'
import store from '../store/index'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: SignInView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/medsearch',
    name: 'medSearch',
    component: MedSearchView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/classes',
    name: 'classes',
    component: ClassesView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/onlineprograms',
    name: 'onlineprograms',
    component: OnlineProgramsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/contact',
    name: 'contact',
    component: ContactView,
    meta: {
      requiresAuth: false
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router
