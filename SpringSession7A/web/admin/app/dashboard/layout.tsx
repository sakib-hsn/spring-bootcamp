'use client'

import { useEffect } from 'react'
import { useRouter } from 'next/navigation'
import Sidebar from '@/components/Sidebar'
import TopBar from '@/components/Topbar'
import { useAuth } from '@/contexts/AuthContext'

export default function DashboardLayout({children}: { children: React.ReactNode }) {
    const { token, role, userName } = useAuth()
    const router = useRouter()

    useEffect(() => {
        console.log("username:", userName)
        if (!token) {
            router.push('/signin')
        }
    }, [token, router])


    return (
        <div className="flex h-screen">
            <Sidebar />
            <div className="flex flex-col flex-1">
                <TopBar />
                <main className="flex-1 overflow-y-auto p-4">
                    {children}
                </main>
            </div>
        </div>
    )
}