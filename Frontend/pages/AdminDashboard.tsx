import React, { useEffect, useState } from "react";
import {
  Users,
  AlertTriangle,
  PhoneCall,
  Droplet,
  Check,
  X,
  Eye,
  Trash2,
  ShieldAlert
} from "lucide-react";
import api from "../services/api";

type EmergencyService = {
  id: number;
  name: string;
  phone: string;
  city: string;
  verified: boolean;
};

const AdminDashboard: React.FC = () => {
  const [activeView, setActiveView] = useState<"ALERTS" | "SERVICES" | "DONORS">(
    "ALERTS"
  );
  const [selectedLocality, setSelectedLocality] = useState<string>("All");
  const [services, setServices] = useState<EmergencyService[]>([]);
  const [loadingServices, setLoadingServices] = useState(false);

  const localities = ["All", "Metropolis", "Gotham", "Star City", "Central City"];

  const mockSosAlerts = [
    { id: 1, user: "John Citizen", time: "2 mins ago", city: "Metropolis", status: "ACTIVE" },
    { id: 2, user: "Bruce Wayne", time: "15 mins ago", city: "Gotham", status: "ACTIVE" },
    { id: 3, user: "Oliver Queen", time: "1 hour ago", city: "Star City", status: "ACTIVE" },
    { id: 4, user: "Barry Allen", time: "3 hours ago", city: "Central City", status: "RESOLVED" }
  ];

  const mockDonors = [
    { id: 1, name: "Alice Smith", group: "O+", city: "Metropolis" },
    { id: 2, name: "Bob Jones", group: "AB-", city: "Gotham" },
    { id: 3, name: "Charlie Brown", group: "A+", city: "Star City" },
    { id: 4, name: "Diana Prince", group: "B+", city: "Central City" }
  ];

  const filteredSosAlerts = mockSosAlerts.filter(
    (alert) => selectedLocality === "All" || alert.city.toLowerCase() === selectedLocality.toLowerCase()
  );

  const filteredServices = services.filter(
    (s) => selectedLocality === "All" || s.city.toLowerCase() === selectedLocality.toLowerCase()
  );

  const filteredDonors = mockDonors.filter(
    (d) => selectedLocality === "All" || d.city.toLowerCase() === selectedLocality.toLowerCase()
  );

  /* ============================
     FETCH EMERGENCY SERVICES
     ============================ */
  useEffect(() => {
    if (activeView === "SERVICES") {
      setLoadingServices(true);
      api
        .get("/emergency-services")
        .then((res) => {
          console.log("Admin services:", res.data);
          setServices(res.data);
        })
        .catch((err) => {
          console.error("Failed to load services", err);
        })
        .finally(() => {
          setLoadingServices(false);
        });
    }
  }, [activeView]);

  return (
    <div className="space-y-8">
      <header className="flex flex-col md:flex-row md:items-center justify-between gap-4 bg-slate-900 p-6 rounded-3xl text-white shadow-xl">
        <div>
          <h1 className="text-2xl font-bold flex items-center gap-2">
            <ShieldAlert className="text-blue-400" />
            Admin Command Center
          </h1>
          <p className="text-blue-200 mt-1">
            Platform oversight and emergency management for selected area
          </p>
        </div>

        <div className="flex items-center gap-3 bg-slate-800/80 backdrop-blur border border-slate-700 p-2 rounded-2xl">
          <label className="text-xs font-bold uppercase tracking-wider text-blue-400 pl-2">Local Command:</label>
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

        <div className="flex gap-2 bg-gray-100 p-1 rounded-xl">
          <button
            onClick={() => setActiveView("ALERTS")}
            className={`px-4 py-2 rounded-lg text-sm font-semibold ${
              activeView === "ALERTS"
                ? "bg-white text-red-600 shadow-sm"
                : "text-gray-500"
            }`}
          >
            SOS Alerts
          </button>
          <button
            onClick={() => setActiveView("SERVICES")}
            className={`px-4 py-2 rounded-lg text-sm font-semibold ${
              activeView === "SERVICES"
                ? "bg-white text-blue-600 shadow-sm"
                : "text-gray-500"
            }`}
          >
            Services
          </button>
          <button
            onClick={() => setActiveView("DONORS")}
            className={`px-4 py-2 rounded-lg text-sm font-semibold ${
              activeView === "DONORS"
                ? "bg-white text-orange-600 shadow-sm"
                : "text-gray-500"
            }`}
          >
            Donors
          </button>
        </div>
      </header>

      {/* ============================
         ALERTS (LOCALITY FILTERED)
         ============================ */}
      {activeView === "ALERTS" && (
        <div className="bg-white rounded-xl border shadow-sm p-6 space-y-4">
          <h3 className="text-lg font-bold flex items-center gap-2 mb-2">
            <AlertTriangle className="text-red-600" />
            SOS Signals in {selectedLocality}
          </h3>

          {filteredSosAlerts.length === 0 ? (
            <p className="text-gray-500 text-sm py-4">No active SOS alerts found in this locality.</p>
          ) : (
            filteredSosAlerts.map((alert) => (
              <div
                key={alert.id}
                className="flex justify-between items-center p-4 bg-gray-50 border border-gray-100 rounded-xl"
              >
                <div>
                  <div className="flex items-center gap-2">
                    <p className="font-semibold text-gray-900">{alert.user}</p>
                    <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-red-100 text-red-700">
                      {alert.city.toUpperCase()}
                    </span>
                  </div>
                  <p className="text-xs text-gray-500">{alert.time}</p>
                </div>
                <div className="flex gap-2">
                  <button className="p-2 text-blue-600 hover:bg-blue-50 rounded-lg">
                    <Eye size={18} />
                  </button>
                  <button className="p-2 text-green-600 hover:bg-green-50 rounded-lg">
                    <Check size={18} />
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      )}

      {/* ============================
         SERVICES (REAL BACKEND & LOCALITY FILTERED)
         ============================ */}
      {activeView === "SERVICES" && (
        <div className="space-y-6">
          {loadingServices && (
            <div className="flex justify-center py-8 text-gray-500">Loading services...</div>
          )}

          {!loadingServices && filteredServices.length === 0 && (
            <div className="bg-white rounded-xl border shadow-sm p-6 text-center text-gray-500 py-12">
              No verified services found in {selectedLocality}.
            </div>
          )}

          {!loadingServices && filteredServices.length > 0 && (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {filteredServices.map((service) => (
                <div
                  key={service.id}
                  className="bg-white p-6 rounded-xl border shadow-sm space-y-4"
                >
                  <div className="flex justify-between items-start">
                    <div className="p-3 bg-blue-50 text-blue-600 rounded-xl">
                      <PhoneCall size={20} />
                    </div>
                    <button className="p-2 text-red-500 hover:bg-red-50 rounded-lg">
                      <Trash2 size={16} />
                    </button>
                  </div>

                  <div>
                    <h4 className="font-bold text-gray-900">{service.name}</h4>
                    <div className="flex items-center gap-2 mt-1">
                      <span className="text-[10px] font-bold px-2 py-0.5 rounded bg-gray-100 text-gray-700">
                        {service.city}
                      </span>
                    </div>
                    <p className="text-sm font-mono text-blue-600 mt-2">
                      {service.phone}
                    </p>
                  </div>

                  <div className="pt-4 border-t flex justify-between text-xs font-bold">
                    <span
                      className={`${
                        service.verified ? "text-green-600" : "text-yellow-600"
                      }`}
                    >
                      {service.verified ? "VERIFIED" : "UNVERIFIED"}
                    </span>
                    <span className="text-gray-400">ACTIVE</span>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* ============================
         DONORS (LOCALITY FILTERED)
         ============================ */}
      {activeView === "DONORS" && (
        <div className="bg-white rounded-xl border shadow-sm p-6 space-y-4">
          <h3 className="text-lg font-bold flex items-center gap-2 mb-2">
            <Droplet className="text-red-600" />
            Donor Applications in {selectedLocality}
          </h3>

          {filteredDonors.length === 0 ? (
            <p className="text-gray-500 text-sm py-4">No donor registrations found in this locality.</p>
          ) : (
            filteredDonors.map((d) => (
              <div
                key={d.id}
                className="flex justify-between items-center p-4 bg-gray-50 border border-gray-100 rounded-xl"
              >
                <div>
                  <div className="flex items-center gap-2">
                    <p className="font-semibold text-gray-900">{d.name}</p>
                    <span className="text-[10px] font-extrabold px-2 py-0.5 rounded bg-red-100 text-red-700">
                      {d.group}
                    </span>
                    <span className="text-[10px] font-bold px-2 py-0.5 rounded bg-gray-100 text-gray-700">
                      {d.city}
                    </span>
                  </div>
                  <p className="text-xs text-gray-500 mt-1">Status: Pending Verification</p>
                </div>
                <div className="flex gap-2">
                  <button className="p-2 bg-green-100 text-green-700 rounded-lg hover:bg-green-200 transition-colors">
                    <Check size={18} />
                  </button>
                  <button className="p-2 bg-red-100 text-red-700 rounded-lg hover:bg-red-200 transition-colors">
                    <X size={18} />
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      )}
    </div>
  );
};

export default AdminDashboard;
