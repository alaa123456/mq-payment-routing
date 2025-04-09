export const menuSideBar = [
  {
    title: 'Tableau de bord',
    icon: 'space_dashboard', 
    path: '/pages/home',

    menuItem: [
      {
        title: 'Overview',
        icon: 'home', 
        path: '/home',
      },
    ],
  },
  {
    title: 'Gestion des partenaires',
    icon: 'handshake',
    menuItem: [
      {
        title: 'Liste des partenaires',
        icon: 'people_alt',
        path: '/partners',
      },
    ],
  },
  {
    title: 'Gestion des messages',
    icon: 'inbox',
    menuItem: [
      {
        title: 'Liste des messages',
        icon: 'format_list_bulleted',
        path: '/messages',
      }
    ],
  }

];