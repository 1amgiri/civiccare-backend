
import React, { useState, useEffect } from 'react';
import {
  PhoneCall, Droplet, AlertTriangle,
  ArrowRight, CheckCircle2, Loader2, AlertCircle
} from 'lucide-react';
import { Link } from 'react-router-dom';
import { Role } from '../types';
import api from '../services/api';

interface DashboardStats {
  activeSosCount: number;
  availableDonorCount: number;
  verifiedServiceCount: number;
}

interface DashboardProps {
  user: any;
}

const Dashboard: React.FC<DashboardProps> = ({ user }) => {
  const [selectedLocality, setSelectedLocality] = useState<string>(user?.city || 'Metropolis');
  const [stats, setStats] = useState<DashboardStats | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const isAdmin = user?.role === Role.ADMIN;

  const localities = ['All', 'Metropolis', 'Gotham', 'Star City', 'Central City'];

  useEffect(() => {
    const fetchStats = async () => {
      setLoading(true);
      try {
        const response = await api.get(`/dashboard/stats`, {
          params: { city: selectedLocality }
        });
        setStats(response.data);
        setError(null);
      } catch (err) {
        setError('Failed to load real-time statistics.');
      } finally {
        setLoading(false);
      }
    };
    fetchStats();
  }, [selectedLocality]);

  const statItems = [
    {
      label: 'Active SOS',
      value: 3, // Static value as requested
      icon: AlertTriangle,
      color: 'text-red-600',
      bg: 'bg-red-50',
    },
    {
      label: 'Available Donors',
      value: stats?.availableDonorCount,
      icon: Droplet,
      color: 'text-blue-600',
      bg: 'bg-blue-50',
    },
    {
      label: 'Verified Services',
      value: stats?.verifiedServiceCount,
      icon: PhoneCall,
      color: 'text-green-600',
      bg: 'bg-green-50',
    },
  ];


  const quickActions = [
    { title: 'Emergency Contacts', desc: 'Find local services', icon: PhoneCall, href: '/emergency', color: 'bg-blue-600' },
    { title: 'Blood Bank', desc: 'Find or register as donor', icon: Droplet, href: '/blood', color: 'bg-red-500' },
    { title: 'SOS Alert', desc: 'Trigger immediate help', icon: AlertTriangle, href: '/sos', color: 'bg-orange-500' },
  ];

  return (
    <div className="space-y-8">
      <header className="flex flex-col md:flex-row md:items-center justify-between gap-4 bg-slate-900 p-6 rounded-3xl text-white shadow-xl">
        <div>
          <h1 className="text-3xl font-extrabold tracking-tight">Citizen Dashboard</h1>
          <p className="text-blue-200 mt-1">Local civic and emergency support tailored for your area</p>
        </div>
        <div className="flex items-center gap-3 bg-slate-800/80 backdrop-blur border border-slate-700 p-2 rounded-2xl">
          <label className="text-xs font-bold uppercase tracking-wider text-blue-400 pl-2">Locality:</label>
          <select
            value={selectedLocality}
            onChange={(e) => setSelectedLocality(e.target.value)}
            className="bg-slate-900 border border-slate-700 text-white text-sm font-semibold rounded-xl px-4 py-2 focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all"
          >
            {localities.map((loc) => (
              <option key={loc} value={loc}>
                {loc}
              </option>
            ))}
          </select>
        </div>
      </header>

      {error && (
        <div className="bg-red-50 border border-red-100 text-red-700 p-4 rounded-xl flex items-center gap-3">
          <AlertCircle size={20} />
          {error}
        </div>
      )}

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {statItems.map((item) => (
          <div key={item.label} className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 flex items-center gap-4">
            <div className={`p-3 rounded-lg ${item.bg} ${item.color}`}>
              <item.icon size={24} />
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500 uppercase tracking-wider">{item.label}</p>
              <div className="text-2xl font-bold text-gray-900">
                {loading ? <Loader2 className="animate-spin text-gray-300" size={20} /> : item.value ?? '—'}
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <div className="space-y-4">
          <h2 className="text-xl font-semibold text-gray-900">Quick Services</h2>
          <div className="grid grid-cols-1 gap-4">
            {quickActions.map((action) => (
              <Link
                key={action.title}
                to={action.href}
                className="group bg-white p-5 rounded-xl border border-gray-200 hover:border-blue-500 hover:shadow-md transition-all flex items-center gap-4"
              >
                <div className={`p-3 rounded-lg ${action.color} text-white`}>
                  <action.icon size={24} />
                </div>
                <div className="flex-1">
                  <h3 className="font-semibold text-gray-900">{action.title}</h3>
                  <p className="text-sm text-gray-500">{action.desc}</p>
                </div>
                <ArrowRight size={20} className="text-gray-300 group-hover:text-blue-500 group-hover:translate-x-1 transition-all" />
              </Link>
            ))}
          </div>
        </div>

        <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
          <h2 className="text-xl font-semibold text-gray-900 mb-4">Latest Updates</h2>
          <div className="space-y-4">
            {[1, 2, 3].map((i) => (
              <div key={i} className="flex gap-4 pb-4 border-b border-gray-50 last:border-0 last:pb-0">
                <div className="flex-shrink-0 mt-1">
                  <CheckCircle2 size={16} className="text-green-500" />
                </div>
                <div>
                  <p className="text-sm text-gray-800">
                    {i === 1 ? 'New Ambulance service verified in South Zone.' : i === 2 ? 'Blood drive scheduled at Civil Hospital this Sunday.' : 'Emergency response time improved by 15% this month.'}
                  </p>
                  <p className="text-xs text-gray-400 mt-1">{i} hour{i > 1 ? 's' : ''} ago</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {isAdmin && (
        <section className="bg-blue-600 rounded-2xl p-8 text-white flex flex-col md:flex-row items-center justify-between gap-6">
          <div className="space-y-2">
            <h2 className="text-2xl font-bold">Admin Privileges Active</h2>
            <p className="text-blue-100">You have access to critical management tools and SOS response systems.</p>
          </div>
          <Link
            to="/admin"
            className="px-6 py-3 bg-white text-blue-600 font-bold rounded-lg hover:bg-blue-50 transition-colors whitespace-nowrap"
          >
            Go to Admin Center
          </Link>
        </section>
      )}
    </div>
  );
};

export default Dashboard;
