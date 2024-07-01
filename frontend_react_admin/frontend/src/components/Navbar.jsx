import { useState } from "react";
import { LogOut, ChevronFirst, ChevronLast } from "lucide-react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { useAuthStore } from "../store/Auth";
import PropTypes from "prop-types";

export function Navbar({ children }) {
  const [expanded, setExpanded] = useState(false);
  const logout = useAuthStore((state) => state.logout);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <nav className="bg-white shadow-lg mt-10 mb-5 pb-4">
      <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16 items-center">
          <div className="flex items-center space-x-4 overflow-x-auto flex-1">
            <div className="flex items-center md:hidden">
              <button
                onClick={() => setExpanded((curr) => !curr)}
                className="inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-blue-500"
              >
                <span className="sr-only">Open main menu</span>
                {expanded ? <ChevronLast /> : <ChevronFirst />}
              </button>
            </div>
            <div className="flex space-x-6">
              {children}
            </div>
          </div>
          <div className="flex-shrink-0 ml-5">
            <button
              onClick={handleLogout}
              className="bg-red-500 text-white px-1 py-2 rounded-md text-sm font-medium hover:bg-red-600 transition duration-150 ease-in-out"
            >
              <LogOut size={20} className="mr-2" />
              Cerrar Sesión
            </button>
          </div>
        </div>
      </div>
      <div className={`${expanded ? 'block' : 'hidden'} md:hidden`}>
        <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3">
          {children}
        </div>
      </div>
    </nav>
  );
}

export function NavbarItem({ icon, text, to }) {
  const location = useLocation();
  const isActive = location.pathname === to;

  return (
    <Link
      to={to}
      className={`${
        isActive
          ? 'bg-indigo-100 text-indigo-800'
          : 'text-gray-600 hover:text-indigo-800 hover:bg-indigo-50'
      } flex items-center px-3 py-2 rounded-md text-sm font-medium transition duration-150 ease-in-out`}
    >
      <div className="flex-shrink-0 w-8 h-8 flex items-center justify-center">
        {icon}
      </div>
      <span className="ml-3">{text}</span>
    </Link>
  );
}

NavbarItem.propTypes = {
  icon: PropTypes.element.isRequired,
  text: PropTypes.string.isRequired,
  to: PropTypes.string.isRequired,
};

Navbar.propTypes = {
  children: PropTypes.node.isRequired,
};
